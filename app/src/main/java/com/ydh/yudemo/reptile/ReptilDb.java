package com.ydh.yudemo.reptile;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * Created by Android on 2018/5/2.
 */
@Table(name = "reptil")
public class ReptilDb {
    @Column(name = "id", isId = true)
    private int id;
    @Column(name = "date")
    private String date;
    @Column(name = "week")
    private String week;
    @Column(name = "issue")
    private String issue;
    @Column(name = "match")
    private String match;
    @Column(name = "home")
    private String home;
    @Column(name = "guest")
    private String guest;
    @Column(name = "isDg")
    private boolean isDg;
    @Column(name = "odds0")
    private String odds0;
    @Column(name = "odds1")
    private String odds1;
    @Column(name = "odds3")
    private String odds3;
    @Column(name = "halfScore")
    private String halfScore;
    @Column(name = "score")
    private String score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHalfScore() {
        return halfScore;
    }

    public void setHalfScore(String halfScore) {
        this.halfScore = halfScore;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public boolean getIsDg() {
        return isDg;
    }

    public void setIsDg(boolean isDg) {
        this.isDg = isDg;
    }

    public String getOdds0() {
        return odds0;
    }

    public void setOdds0(String odds0) {
        this.odds0 = odds0;
    }

    public String getOdds1() {
        return odds1;
    }

    public void setOdds1(String odds1) {
        this.odds1 = odds1;
    }

    public String getOdds3() {
        return odds3;
    }

    public void setOdds3(String odds3) {
        this.odds3 = odds3;
    }

    @Override
    public String toString() {
        return "ReptilDb{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", issue='" + issue + '\'' +
                ", match='" + match + '\'' +
                ", home='" + home + '\'' +
                ", guest='" + guest + '\'' +
                ", isDg='" + isDg + '\'' +
                ", odds0='" + odds0 + '\'' +
                ", odds1='" + odds1 + '\'' +
                ", odds3='" + odds3 + '\'' +
                '}';
    }
}
