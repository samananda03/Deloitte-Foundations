
package com.cloudthat.bankingapp.event;

import com.cloudthat.bankingapp.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class RegistrationCompleteEvent extends ApplicationEvent {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private User user;
    private String applicationUrl;

    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        // TODO Auto-generated constructor stub
        this.user = user;
        this.applicationUrl = applicationUrl;
    }
}
