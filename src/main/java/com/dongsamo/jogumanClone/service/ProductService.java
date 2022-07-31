package com.dongsamo.jogumanClone.service;

import com.dongsamo.jogumanClone.component.FileHandler;
import com.dongsamo.jogumanClone.domain.product.Product;
import com.dongsamo.jogumanClone.domain.product.ProductRepository;
import com.dongsamo.jogumanClone.domain.productImage.ProductImage;
import com.dongsamo.jogumanClone.domain.productImage.ProductImageRepository;
import com.dongsamo.jogumanClone.dto.ProductDto;
import com.dongsamo.jogumanClone.dto.ProductImageDto;
import com.dongsamo.jogumanClone.dto.ProductSimpleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final FileHandler fileHandler = new FileHandler();

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
    public ProductDto save(Product productDto, List<MultipartFile> files) throws Exception { //productImageDto추가해야함
        // 먼저 Product 를 저장하고 그 객체를 가지고 있는다
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .amount(productDto.getAmount()).
                build();

        productRepository.save(product);

        // 파일을 저장하고 그 ProductImage 에 대한 list 를 가지고 있는다
        List<ProductImage> list = fileHandler.parseFileInfo(product, files);

        List<ProductImageDto> pictureBeans;

        if (list.isEmpty()) {
            return new ProductDto(product, new ArrayList<>());
            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else {
            pictureBeans = new ArrayList<>();
            for (ProductImage productImage : list) {
                pictureBeans.add(new ProductImageDto(productImageRepository.save(productImage)));
            }
        }

        //Product.setReported_date(new Date().toString());

        return new ProductDto(product, pictureBeans);
    }

    @Transactional
    public void deleteById(Long id) { //연관된 사진도 삭제해야함
        productRepository.deleteById(id);
    }
}
