package za.co.xeon.domain.dto;

import java.util.Arrays;

public class ReadAttachmentDto {

    private String mimeType;
    private byte[] attachment;

    public ReadAttachmentDto(final String mimeType, final byte[] attachment) {
        this.mimeType = mimeType;
        this.attachment = attachment;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(final byte[] attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "ReadAttachmentDto{" +
            "mimeType='" + mimeType + '\'' +
            ", attachment=" + Arrays.toString(attachment) +
            '}';
    }
}
