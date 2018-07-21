package neo.com.noibai_airport.View.Activity.ActivityShops;

import java.util.List;

import neo.com.noibai_airport.Model.Comments;

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
public interface ImlComment {
    interface Presenter{
        void get_comment(String sUserId, String sServiceId, String subId);
        void add_comment(String sUserId, String sMemberid, String sServiceid,String sComment, String sStar, String sSubid);
    }
    interface View{
        void show_list_comment(List<Comments> mLisComments);
        void show_result_addComments();
        void show_error_addComments();
        void show_error_api();
    }
}
