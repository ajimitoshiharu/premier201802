package premier20180201;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO; 

public class CreateQRCode {
	public static void main(String[] args) throws WriterException, IOException {

        //QRコード生成したい文字列
        String giantsUrl = "http://www.giants.jp/top.html";
        String amazonUrl = "https://www.amazon.co.jp/dp/B01BHPEC9G";
        String cosmeUrl = "http://www.cosme.net/product/product_id/10023860/top";
        //サイズ(ピクセル)
        int width = 160;
        int height = 160;
        //画像ファイルの保存先
        String filePathOfGiants = "src/qr_code_giants.png";
        String filePathOfAmazon = "src/qr_code_amazon.png";
        String filePathOfCosme = "src/qr_code_cosme.png";
        String fileFormat = "png";
        
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        // QRCode作成
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrixOfGiantsUrl = writer.encode(giantsUrl, BarcodeFormat.QR_CODE, width, height, hints);
        BitMatrix bitMatrixOfAmazonUrl = writer.encode(amazonUrl, BarcodeFormat.QR_CODE, width, height, hints);
        BitMatrix bitMatrixOfCosmeUrl = writer.encode(cosmeUrl, BarcodeFormat.QR_CODE, width, height, hints);
        BufferedImage imageOfGiantsUrl = MatrixToImageWriter.toBufferedImage(bitMatrixOfGiantsUrl);
        BufferedImage imageOfAmazonUrl = MatrixToImageWriter.toBufferedImage(bitMatrixOfAmazonUrl);
        BufferedImage imageOfCosmeUrl = MatrixToImageWriter.toBufferedImage(bitMatrixOfCosmeUrl);

        //ファイルへの保存処理
        ImageIO.write(imageOfGiantsUrl, fileFormat, new File(filePathOfGiants));
        ImageIO.write(imageOfAmazonUrl, fileFormat, new File(filePathOfAmazon));
        ImageIO.write(imageOfCosmeUrl, fileFormat, new File(filePathOfCosme));
    }
}