package domain;

import java.time.LocalDate;
import java.time.Month;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private final Pattern pattern = Pattern.compile("[a-zA-z]+[.0-9a-zA-Z]*@[a-zA-z]+[0-9a-zA-Z].[a-zA-z]+");

    private final String name;
    private final LocalDate birthdate;
    private final String email;


    public User(String name, LocalDate birthdate, String email) {
        this.name = name;
        this.birthdate = birthdate;
        this.email = email;
        validate();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isBirthday(LocalDate today) {
        return isAn29FebruaryException(today) || isTodayBirthday(today);
    }

    private void validate() {
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) throw new InvalidMailException("Invalid email: " + email);
    }

    private boolean isTodayBirthday(LocalDate today) {
        return birthdate.getDayOfMonth() == today.getDayOfMonth() && birthdate.getMonth() == today.getMonth();
    }

    private boolean isAn29FebruaryException(LocalDate today) {
        return !today.isLeapYear() && birthdate.isLeapYear() && isFebruary(birthdate, 29) && isFebruary(today, 28);
    }

    private boolean isFebruary(LocalDate birthdate, int day) {
        return birthdate.getMonth() == Month.FEBRUARY && birthdate.getDayOfMonth() == day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(birthdate, user.birthdate) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthdate, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                '}';
    }
}
