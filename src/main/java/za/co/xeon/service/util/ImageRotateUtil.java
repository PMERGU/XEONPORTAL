//package za.co.xeon.service.util;
//
//import java.io.ByteArrayOutputStream;
//
//import magick.ImageInfo;
//import magick.MagickImage;
//
//import org.apache.sanselan.Sanselan;
//import org.apache.sanselan.common.IImageMetadata;
//import org.apache.sanselan.formats.jpeg.JpegImageMetadata;
//import org.apache.sanselan.formats.jpeg.exifRewrite.ExifRewriter;
//import org.apache.sanselan.formats.tiff.TiffImageMetadata;
//import org.apache.sanselan.formats.tiff.constants.TiffConstants;
//import org.apache.sanselan.formats.tiff.write.TiffOutputDirectory;
//import org.apache.sanselan.formats.tiff.write.TiffOutputField;
//import org.apache.sanselan.formats.tiff.write.TiffOutputSet;
//
//public class ImageRotateUtil {
//
//    /**
//     * Flopping direction Constants
//     */
//    private static final int NONE = 0;
//    private static final int HORIZONTAL = 1;
//    private static final int VERTICAL = 2;
//
//    /**
//     * The operations that depend on EXIF:Orientation value.
//     * { Rotation degrees, Flopping direction}
//     *
//     * Note: You must use (EXIF:Orientation value - 1) value as index
//     *       because EXIF:Orientation value starts from 1.
//     */
//    private static final int[][] OPERATIONS = new int[][] {
//        new int[] {  0, NONE},
//        new int[] {  0, HORIZONTAL},
//        new int[] {180, NONE},
//        new int[] {  0, VERTICAL},
//        new int[] { 90, HORIZONTAL},
//        new int[] { 90, NONE},
//        new int[] {-90, HORIZONTAL},
//        new int[] {-90, NONE},
//    };
//
//    /**
//     * Automatically corrects the orientation of image
//     * by interpreting exif:Orientation value
//     *
//     * Note: Nothing to be done if original image is already processed
//     * or something is wrong.
//     *
//     * @param origImage - original image
//     * @param info - image info needed to image processing
//     * @return - raw bytes of converted image
//     * @throws Exception
//     */
//    public static byte[] rotateImageByExif(MagickImage origImage, ImageInfo info) throws Exception {
//
//        ByteArrayOutputStream iaos = null;
//        MagickImage newImage = null;
//        MagickImage rotatedImage = null;
//
//        try {
//            //  Do nothing in case EXIF:Orientation value already 1.
//            int index = Integer.parseInt(origImage.getImageAttribute("EXIF:Orientation")) - 1;
//            if (index == 0) {
//                return origImage.imageToBlob(info);
//            }
//            int degrees = OPERATIONS[index][0];
//            if (degrees != 0) {
//                //  rotate image
//                rotatedImage = origImage.rotateImage(degrees);
//                //  flop image
//                switch (OPERATIONS[index][1]) {
//                    case HORIZONTAL:
//                        newImage = rotatedImage.flopImage();
//                        break;
//                    case VERTICAL:
//                        newImage = rotatedImage.flipImage();
//                        break;
//                    default:
//                        newImage = rotatedImage;
//                }
//            } else {
//                //  In case of no rotation, flop original image.
//                switch (OPERATIONS[index][1]) {
//                    case HORIZONTAL:
//                        newImage = origImage.flopImage();
//                        break;
//                    case VERTICAL:
//                        newImage = origImage.flipImage();
//                        break;
//                    default:
//                        newImage = origImage;
//                }
//            }
//            //  update EXIF:Orientation value.
//            //  This process is required to prevent duplicate [rotation|flop]
//            byte[] origImageByte = newImage.imageToBlob(info);
//            TiffOutputSet outputSet = null;
//            IImageMetadata metadata = Sanselan.getMetadata(origImageByte);
//            JpegImageMetadata jpegMetadata = (JpegImageMetadata) metadata;
//            if (null != jpegMetadata) {
//                TiffImageMetadata exif = jpegMetadata.getExif();
//                if (null != exif) {
//                    outputSet = exif.getOutputSet();
//                }
//            }
//            if (null == outputSet) {
//                outputSet = new TiffOutputSet();
//            }
//            //  delete existing Orientation Tag from outputSet
//            outputSet.removeField(TiffConstants.EXIF_TAG_ORIENTATION);
//            outputSet.removeField(TiffConstants.TIFF_TAG_ORIENTATION);
//            //  newly generate Orientation Tag
//            TiffOutputField orientation_tag = TiffOutputField.create(
//                TiffConstants.EXIF_TAG_ORIENTATION,
//                outputSet.byteOrder, new Integer(1));
//            iaos = new ByteArrayOutputStream();
//            TiffOutputDirectory exifDirectory = outputSet.getOrCreateExifDirectory();
//            exifDirectory.removeField(TiffConstants.EXIF_TAG_ORIENTATION);
//            exifDirectory.removeField(TiffConstants.TIFF_TAG_ORIENTATION);
//            exifDirectory.add(orientation_tag);
//            //  finally update Exif.
//            new ExifRewriter().updateExifMetadataLossless(
//                origImageByte, iaos, outputSet
//            );
//
//            return iaos.toByteArray();
//
//        } catch (NumberFormatException e) {
//            //  ignore in case of no EXIF:Orientation
//            return origImage.imageToBlob(info);
//        } catch (ArrayIndexOutOfBoundsException e1) {
//            //  ignore in case of invalid EXIF:Orientation
//            return origImage.imageToBlob(info);
//        } catch (Exception e2) {
//            throw e2;  //  image processing related Exception
//        } finally {
//            //  close useless resource.
//            if (iaos != null) iaos.close();
//            if (origImage != null) origImage.destroyImages();
//            if (rotatedImage != null) rotatedImage.destroyImages();
//            if (newImage != null) newImage.destroyImages();
//            iaos = null;
//            origImage = null;
//            rotatedImage = null;
//            newImage = null;
//        }
//    }
//}
