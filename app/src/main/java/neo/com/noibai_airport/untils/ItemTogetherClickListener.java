package neo.com.noibai_airport.untils;

public interface ItemTogetherClickListener<T> {
    void onClickItem(int position, T item);
    void onClickPhoneItem(int position, T item);
}
