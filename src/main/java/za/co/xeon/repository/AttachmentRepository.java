package za.co.xeon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import za.co.xeon.domain.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    @Query("select a from Attachment a where a.uuid = ?1 and a.activated = true")
    Optional<Attachment> findByUuidAndActivated(String uuid);

    @Query("select a from Attachment a where a.uuid = ?1 and a.activated = ?2")
    Optional<Attachment> findByUuidAndActivated(String uuid, Boolean activated);

    @Query("select a from Attachment a where a.deliveryNumber = ?1 and a.activated = true")
    List<Attachment> findByDeliveryNumberAndActivated(String deliveryNumber);

    @Query("select a from Attachment a where a.deliveryNumber = ?1 and a.visible = ?2 and a.activated = true")
    List<Attachment> findByDeliveryNumberAndVisibleAndActivated(String deliveryNumber, Boolean visible);
}
