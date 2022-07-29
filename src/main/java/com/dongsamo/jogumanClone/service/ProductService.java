package com.dongsamo.jogumanClone.service;

import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
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

    @Transactional
    public List<ProductSimpleDto> findSimpleAll() {
        List<ProductSimpleDto> productSimpleDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();

        for(int i=0;i<productList.size();i++) {
            productSimpleDtoList.add(new ProductSimpleDto(productList.get(i)));
        }

        return productSimpleDtoList;
    }

    @Transactional
    public List<ProductDto> findAll() {
        List<ProductDto> productDtoList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();

        for(int i=0;i<productList.size();i++) {
            productDtoList.add(new ProductDto(productList.get(i)));
        }

        return productDtoList;
    }

    @Transactional
    public ProductDto findById(Long id) {
        return new ProductDto(productRepository.findById(id).get());
    }

    @Transactional
    public ProductSimpleDto findSimpleById(Long id) {
        return new ProductSimpleDto(productRepository.findById(id).get());
    }

    @Transactional
    public ProductDto save(ProductDto productDto) {
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
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
