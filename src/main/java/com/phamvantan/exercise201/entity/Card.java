package com.phamvantan.exercise201.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties; // ✅ Thêm dòng này
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cards")
@EntityListeners(AuditingEntityListener.class)
// ✅ Bỏ qua các proxy của Hibernate khi chuyển sang JSON (fix lỗi ByteBuddyInterceptor)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Card {
    
    // id UUID NOT NULL DEFAULT
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Tự sinh UUID cho ID
    private UUID id;
    
    // customer_id UUID REFERENCES customers(id)
    // Thiết lập mối quan hệ Many-to-One với Customer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false, // customer_id là NOT NULL
            referencedColumnName = "id", // Tham chiếu đến cột 'id' của bảng 'customers'
            columnDefinition = "UUID") // Kiểu dữ liệu là UUID
    private Customer customer;

    // Getter/Setter cho customer_id (để thuận tiện cho việc tạo JSON request)
    // Trường này KHÔNG được lưu vào DB mà chỉ dùng cho Request/Response
    @Transient // Không map trường này vào cột trong DB
    private UUID customerId;

    // Phương thức life-cycle để gán customer từ customerId trước khi persist
    @PrePersist
    @PreUpdate
    public void setCustomerFromId() {
        if (this.customerId != null) {
            // Chỉ cần set ID cho Customer object, JPA sẽ tự động quản lý
            this.customer = Customer.builder().id(this.customerId).build();
        }
    }
}
