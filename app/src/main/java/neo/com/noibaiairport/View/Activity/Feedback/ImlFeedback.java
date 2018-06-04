package neo.com.noibaiairport.View.Activity.Feedback;

import java.util.List;

import neo.com.noibaiairport.Model.Feedback;

/**
 * @author Quốc Huy
 * @version 1.0.0
 * @description
 * @desc Developer NEO Company.
 * @created ${Date}
 * @updated ${Date}
 * @modified by
 * @updated on ${Date}
 * @since 1.0
 */
public interface ImlFeedback {
    interface Presenter{
        void get_feedback(String sUserId, String sNickname, String sEmail);
    }
    interface View{
        void show_feedback(List<Feedback >mLisFeedback);
    }
}
