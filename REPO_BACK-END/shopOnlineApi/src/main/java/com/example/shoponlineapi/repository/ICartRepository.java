package com.example.shoponlineapi.repository;

import com.example.shoponlineapi.model.product.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ICartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "select * from cart where user_id = :idUser and delete_status = false", nativeQuery = true)
    List<Cart> getCartById(@Param("idUser") int idUser);

    @Query(value = "select * from cart where user_id = :idUser and product_id = :productId", nativeQuery = true)
    Cart getCartByProductAndUser(@Param("idUser") int idUser, @Param("productId") int productId);

    @Query(value = "select * from cart where product_id = :productId and delete_status = false", nativeQuery = true)
    Cart getCartByProduct( @Param("productId") int productId);

    @Modifying
    @Query(value = "update cart set quantity = quantity + 1 where product_id = :productId and user_id = :idUser", nativeQuery = true)
    void upQuantity(@Param("idUser") int idUser, @Param("productId") int productId);

    @Modifying
    @Query(value = "update cart set quantity = quantity - 1 where product_id = :productId and user_id = :idUser", nativeQuery = true)
    void downQuantity(@Param("idUser") int idUser, @Param("productId") int productId);

    @Modifying
    @Query(value = "delete from cart where product_id = :productId and user_id = :idUser", nativeQuery = true)
    void delete(@Param("idUser") int idUser, @Param("productId") int productId);
}
