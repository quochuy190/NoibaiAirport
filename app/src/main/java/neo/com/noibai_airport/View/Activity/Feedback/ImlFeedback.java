package neo.com.noibai_airport.View.Activity.Feedback;

import java.util.List;

import neo.com.noibai_airport.Model.Feedback;

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
        void add_new_feedback(String sUserId, String sNickname, String sEmail, String sContent, String sPublic);

    }
    interface View{
        void show_feedback(List<Feedback >mLisFeedback);
        void show_result_addfeedback();
        void show_error_addfeedback();
    }
}
