package za.co.xeon.web.rest.dto;

/**
 * Created by Derick on 9/18/2016.
 */
public class Contact {
    public enum Action {
        LOGISTICS("quote on logistics services"), WAREHOUSING("more info on your warehousing solutions"), OTHER("other request");
        private String desc;

        Action(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    private Action action;
    private String firstName;
    private String lastName;
    private String email;
    private String message;

    public Contact(Action action, String firstName, String lastName, String email, String message) {
        this.action = action;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.message = message;
    }

    public Contact() {
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
