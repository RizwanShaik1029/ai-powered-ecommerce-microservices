package com.srb.ai_service.service;

import com.srb.ai_service.feignclient.ProductClient;
import com.srb.product.model.Product;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class Ai_ServiceImpl {

    private ChatClient chatClient;

    private ProductClient productClient;

    private VectorStore vectorStore;

    public Ai_ServiceImpl(ChatClient chatClient,
                          VectorStore vectorStore,
                          ProductClient productClient)
    {
        this.chatClient=chatClient;
        this.vectorStore=vectorStore;
        this.productClient= productClient;
    }


    public void addListOfData(Product product)
    {
        String content = product.getName() + " " + product.getDescription();
        Document document = new Document(
                content,
                Map.of(
                        "productId", product.getId(),
                        "price", product.getPrice(),
                        "category", product.getBrand(),
                        "active", product.getName()
                )
        );

        vectorStore.add(List.of(document));
    }

    public List<Product> getAllProductDetails(String query)
    {
        SearchRequest searchRequest  = SearchRequest.builder()
                .similarityThreshold(0.6)
                .query(query)
                .build();


        List<Document> dlst =vectorStore.similaritySearch(searchRequest);

        List<Long> productId = dlst.stream().map(doc -> Long.valueOf(doc.getMetadata().get("productId").toString())).toList();

        return productClient.getProductsByIds(productId);
    }



}
