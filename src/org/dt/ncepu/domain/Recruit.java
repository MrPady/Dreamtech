package org.dt.ncepu.domain;

/**
 * Created by Administrator on 2017/09/18 0018.
 */
public class Recruit {
    private boolean isRecruiting;

    public Recruit(boolean isRecruiting) {
        this.isRecruiting = isRecruiting;
    }

    public Recruit() {
    }

    public boolean isRecruiting() {

        return isRecruiting;
    }

    public void setRecruiting(boolean recruiting) {
        isRecruiting = recruiting;
    }
}
