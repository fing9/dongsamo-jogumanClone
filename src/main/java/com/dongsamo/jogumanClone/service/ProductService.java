package com.dongsamo.jogumanClone.service;

import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import com.dongsamo.jogumanClone.domain.productImage.ProductImageRepository;
import com.dongsamo.jogumanClone.dto.ProductDto;
import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    ProductImage productImage = new ProductImage(); // 객체가 생성되기 전에 그 객체를 참조하면 NullPointerException이 발생한다.

    @Transactional
    public List<ProductSimpleDto> findSimpleAll() {
        List<ProductSimpleDto> productSimpleDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();

        for(int i=0;i<productList.size();i++) {
            productSimpleDtoList.add(new ProductSimpleDto(productList.get(i), productImage.toDtoList(productList.get(i).getProductImages())));
        }

        return productSimpleDtoList;
    }

    @Transactional
    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();

        for(int i=0;i<productList.size();i++) {
            productDtoList.add(new ProductDto(productList.get(i), productImage.toDtoList(productList.get(i).getProductImages())));
        }

        return productDtoList;
    }

    @Transactional
    public ProductDto findById(Long id) {
        return new ProductDto(productRepository.findById(id).get(), productImage.toDtoList(productRepository.findById(id).get().getProductImages()));
    }

    @Transactional
    public ProductSimpleDto findSimpleById(Long id) {
        return new ProductSimpleDto(productRepository.findById(id).get(), productImage.toDtoList(productRepository.findById(id).get().getProductImages()));
    }

    @Transactional
    public ProductDto save(ProductDto productDto) { //productImageDto추가해야함
        productRepository.save(Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .amount(productDto.getAmount()).
                build());

        return productDto;
    }

    @Transactional
    public void deleteById(Long id) { //연관된 사진도 삭제해야함
        productRepository.deleteById(id);
    }
}
