package com.phamvantan.exercise201.service.impl;

import org.springframework.stereotype.Service;
import com.phamvantan.exercise201.entity.Card;
import com.phamvantan.exercise201.entity.Customer;
import com.phamvantan.exercise201.repository.CardRepository;
import com.phamvantan.exercise201.service.CardService;
import java.util.List;
import java.util.UUID;

@Service
public class CardServiceImpl implements CardService {
    
    private final CardRepository cardRepository;
    
    // Tái sử dụng CustomerRepository để kiểm tra sự tồn tại của Customer
    // Hoặc chỉ cần dựa vào JPA để ném exception nếu Customer không tồn tại khi tạo Card
    // Tuy nhiên, việc kiểm tra trước sẽ cung cấp thông báo lỗi rõ ràng hơn
    // Tuy nhiên, theo yêu cầu chỉ cần 5 file cho bảng cards, ta sẽ dựa vào JPA
    // Hoặc giả định customerId trong request là hợp lệ và tồn tại.
    // Nếu muốn kiểm tra sự tồn tại của Customer:
    // private final CustomerRepository customerRepository;
    
    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Card findById(UUID id) {
        return cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found with id: " + id));
    }

    @Override
    public Card create(Card card) {
        // Logíc kiểm tra sự tồn tại của customer_id có thể thêm vào đây
        // Nhưng nếu Card entity đã được thiết lập đúng, JPA sẽ tự động ném exception
        // nếu customer_id không tồn tại (REFERENCES customers(id)).
        return cardRepository.save(card);
    }

    @Override
    public Card update(UUID id, Card card) {
        Card existing = findById(id);
        
        // Chỉ cập nhật customer_id nếu nó được cung cấp trong request
        if (card.getCustomerId() != null) {
            existing.setCustomer(Customer.builder().id(card.getCustomerId()).build());
        }
        
        // Không có trường dữ liệu khác ngoài ID và customer_id để update trong bảng cards
        // Tuy nhiên, để tuân thủ logic update cơ bản, ta vẫn gọi save.
        return cardRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        if (!cardRepository.existsById(id)) {
            throw new RuntimeException("Card not found with id: " + id);
        }
        cardRepository.deleteById(id);
    }
}
