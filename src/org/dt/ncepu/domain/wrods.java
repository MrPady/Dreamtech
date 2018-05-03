package org.dt.ncepu.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/08/09 0009.
 */
public class wrods implements Serializable {
    private String words;
    private long time;
    private Person person;

    public wrods(String words, long time, Person person) {
        this.words = words;
        this.time = time;
        this.person = person;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
