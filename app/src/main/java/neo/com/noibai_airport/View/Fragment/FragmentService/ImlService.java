package neo.com.noibai_airport.View.Fragment.FragmentService;

import java.util.List;

import neo.com.noibai_airport.Model.CategoryService;
import neo.com.noibai_airport.Model.CategoryShops;
import neo.com.noibai_airport.Model.ObjMenu;
import neo.com.noibai_airport.Model.Product;

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
public interface ImlService {
    interface Presenter{
        void get_service_list(String sUserId);
        void get_service_shops(String sUserId);
        void get_shop_menu(String sUserId, String sIdShop);
        void get_list_product(String sUserId, String sIdProduct);
    }
    interface View{
        void show_list_service(List<CategoryService> lisCaService);
        void show_list_shop(List<CategoryShops> lisCaShop);
        void show_error_api(String sError);
        void show_list_menu_shop(List<ObjMenu> lisMenuShop );
        void show_list_product(List<Product> lisProduct );
    }
}
