package pl.wit.shop.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wit.shop.shared.exception.NotFoundException;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameAndCategoryName(String name, String categoryName);

    Optional<Product> findByUuid(UUID uuid);

    default Product getByUuid(UUID uuid) {
        return findByUuid(uuid)
                .orElseThrow(() -> new ProductNotFoundException(uuid));
    }

    class ProductNotFoundException extends NotFoundException {
        ProductNotFoundException(UUID uuid) {
            super(String.format("Product with uuid: %s not found", uuid));
        }
    }
}
