package utils;

public class Agenda {

    private boolean weekly;
    private boolean eachTwoWeeks;   //variables used to identify the payment agenda type.
    private boolean monthly;

    private boolean lastDay;
    private boolean weekToBePaid;

    private int monthDay;
    private int weekDay;

    public boolean isWeekly() {
        return weekly;
    }

    public void setWeekly(boolean weekly) {
        this.weekly = weekly;
    }

    public boolean isEachTwoWeeks() {
        return eachTwoWeeks;
    }

    public void setEachTwoWeeks(boolean eachTwoWeeks) {
        this.eachTwoWeeks = eachTwoWeeks;
    }

    public boolean isMonthly() {
        return monthly;
    }

    public void setMonthly(boolean monthly) {
        this.monthly = monthly;
    }

    public boolean isLastDay() {
        return lastDay;
    }

    public void setLastDay(boolean lastDay) {
        this.lastDay = lastDay;
    }

    public int getMonthDay() {
        return monthDay;
    }

    public void setMonthDay(int monthDay) {
        this.monthDay = monthDay;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public boolean isWeekToBePaid() {
        return weekToBePaid;
    }

    public void setWeekToBePaid(boolean weekToBePaid) {
        this.weekToBePaid = weekToBePaid;
    }
}
