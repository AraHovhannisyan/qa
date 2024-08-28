package am.fillandgo.aspects;

import am.fillandgo.models.BaseEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Aspect class that intercepts save and update operations in the dao package
 * and sets audit fields (createdAt, updatedAt, createdBy, updatedBy) on the entities.
 */
@Aspect
@Component
public class EntityAuditAspect {


    /**
     * Sets audit fields (createdAt, updatedAt, createdBy, updatedBy) on the entities.
     * @param joinPoint the join point representing the intercepted method
     */
    @Before("execution(* am.fillandgo.dao.*.save*(..)) || execution(* am.fillandgo.dao.*.update*(..))")
    public void setAuditFields(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        String currentUser = getCurrentUser();

        for (Object arg : args) {
            if (arg instanceof BaseEntity entity) {

                if (entity.getCreatedAt() == null) {
                    entity.setCreatedAt(LocalDateTime.now().toString());
                    entity.setCreatedBy(currentUser);
                }

                entity.setUpdatedAt(LocalDateTime.now().toString());
                entity.setUpdatedBy(currentUser);
            }
        }
    }

    /**
     * Retrieves the current user.
     * @return A String representing the current user.
     */
    private String getCurrentUser() {
        //Todo Implement logic to get the current user, e.g., from security context or session.
        return "system";  // Replace this with actual user retrieval logic.
    }
}