package com.mondi.FocusApp.config;

import com.mondi.FocusApp.model.UserPrincipal;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class OwnershipFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // Only apply ownership checks to specific paths
        if (requiresOwnershipCheck(request)) {

            // Extract user ID from the request
            Long requestedUserId = extractUserIdFromRequest(request);

            if (requestedUserId != null) {
                // Get the authenticated user's ID
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null && authentication.isAuthenticated()) {
                    Object principal = authentication.getPrincipal();
                    Long currentUserId = null;

                    if (principal instanceof UserPrincipal) {
                        currentUserId = ((UserPrincipal) principal).getId();
                    } else if (principal instanceof UserDetails) {
                        // If you have other implementations of UserDetails
                        // Handle accordingly
                    } else {
                        // Handle other cases if necessary
                    }

                    // Perform ownership check
                    if (currentUserId != null && !requestedUserId.equals(currentUserId)) {
                        // User does not own the resource; deny access
                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "You are not authorized to access this resource.");
                        return;
                    }
                } else {
                    // User is not authenticated; deny access
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You need to be authenticated to access this resource.");
                    return;
                }
            }
        }

        // Continue with the next filter in the chain
        filterChain.doFilter(request, response);
    }

    private boolean requiresOwnershipCheck(HttpServletRequest request) {
        String method = request.getMethod();
        String uri = request.getRequestURI();

        // Define the patterns and methods that require ownership checks
        // For example, any request to /api/users/{id} with GET, PUT, DELETE methods
        if (uri.matches("/api/users/\\d+")
                && ("GET".equalsIgnoreCase(method) || "PUT".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method))) {
            return true;
        }

        // Add other conditions if needed
        return false;
    }

    private Long extractUserIdFromRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        // Assuming the URI is like /api/users/{id}
        // Extract the {id} part using regex
        Pattern pattern = Pattern.compile("/api/users/(\\d+)");
        Matcher matcher = pattern.matcher(uri);
        if (matcher.find()) {
            String userIdStr = matcher.group(1);
            try {
                return Long.parseLong(userIdStr);
            } catch (NumberFormatException e) {
                // Invalid user ID in the path
            }
        }
        return null;
    }
}
