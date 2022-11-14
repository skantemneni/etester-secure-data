package com.etester.data.domain.util.compress;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class JdkDataCompressorImpl implements DataCompressor {

	public JdkDataCompressorImpl() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rf.web.xqee.server.util.compress.DataCompressor#compress(byte[])
	 */
	@Override
	public byte[] compress(byte[] bytesToCompress) {
		Deflater deflater = new Deflater();
		deflater.setInput(bytesToCompress);
		deflater.finish();

		byte[] bytesCompressed = new byte[Short.MAX_VALUE];

		int numberOfBytesAfterCompression = deflater.deflate(bytesCompressed);

		byte[] returnValues = new byte[numberOfBytesAfterCompression];

		System.arraycopy(bytesCompressed, 0, returnValues, 0,
				numberOfBytesAfterCompression);

		return returnValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rf.web.xqee.server.util.compress.DataCompressor#compress(java.lang
	 * .String)
	 */
	@Override
	public byte[] compress(String stringToCompress) {
		byte[] returnValues = null;

		try {

			returnValues = this.compress(stringToCompress.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}

		return returnValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rf.web.xqee.server.util.compress.DataCompressor#decompress(byte[])
	 */
	@Override
	public byte[] decompress(byte[] bytesToDecompress) {
		
		if (bytesToDecompress == null) {
			return null;
		}
		
		byte[] returnValues = null;

		Inflater inflater = new Inflater();

		int numberOfBytesToDecompress = bytesToDecompress.length;

		inflater.setInput(bytesToDecompress, 0, numberOfBytesToDecompress);

		int bufferSizeInBytes = numberOfBytesToDecompress;

		int numberOfBytesDecompressedSoFar = 0;
		List<Byte> bytesDecompressedSoFar = new ArrayList<Byte>();

		try {
			while (inflater.needsInput() == false) {
				byte[] bytesDecompressedBuffer = new byte[bufferSizeInBytes];

				int numberOfBytesDecompressedThisTime = inflater
						.inflate(bytesDecompressedBuffer);

				numberOfBytesDecompressedSoFar += numberOfBytesDecompressedThisTime;

				for (int b = 0; b < numberOfBytesDecompressedThisTime; b++) {
					bytesDecompressedSoFar.add(bytesDecompressedBuffer[b]);
				}
			}

			returnValues = new byte[bytesDecompressedSoFar.size()];
			for (int b = 0; b < returnValues.length; b++) {
				returnValues[b] = (byte) (bytesDecompressedSoFar.get(b));
			}

		} catch (DataFormatException dfe) {
			dfe.printStackTrace();
		}

		inflater.end();

		return returnValues;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.rf.web.xqee.server.util.compress.DataCompressor#decompressToString
	 * (byte[])
	 */
	@Override
	public String decompressToString(byte[] bytesToDecompress) {
		byte[] bytesDecompressed = this.decompress(bytesToDecompress);

		String returnValue = null;

		try {
			returnValue = new String(bytesDecompressed, 0,
					bytesDecompressed.length, "UTF-8");
		} catch (UnsupportedEncodingException uee) {
			uee.printStackTrace();
		}

		return returnValue;
	}

	public static void main(String[] args) {
		DataCompressor compressor = new JdkDataCompressorImpl();

		String stringToCompress = "This is a test!";
		// String stringToCompress =
		// "When in the course of human events, it becomes necessary for one people to dissolve the bands that bind them...";

		System.out.println("stringToCompress'" + stringToCompress + "'");

		byte[] bytesCompressed = compressor.compress(stringToCompress);

		System.out.println("bytesCompressed");
		writeBytesAsHexadecimal(bytesCompressed);

		String stringDecompressed = compressor
				.decompressToString(bytesCompressed);

		System.out.println("stringDecompressed'" + stringDecompressed + "'");
	}

	/**
	 * 
	 * @param bytesToWrite
	 */
	public static void writeBytesAsHexadecimal(byte[] bytesToWrite) {
		int numberOfBytes = bytesToWrite.length;
		int bytesPerLine = 16;
		int bytesPerColumn = 8;
		int numberOfColumns = bytesPerLine / bytesPerColumn;
		int linesPerBlock = 16;

		int numberOfLines = (int) Math.ceil((double) numberOfBytes
				/ (double) bytesPerLine);

		int widthOfDataOnDisplayInChars = numberOfColumns
				* (bytesPerColumn * 3 + 1) - 1;

		String horizontalRule = "";

		for (int i = 0; i < widthOfDataOnDisplayInChars; i++) {
			horizontalRule += "=";
		}

		System.out.println(horizontalRule);

		for (int y = 0; y < numberOfLines; y++) {
			if (y > 0 && y % linesPerBlock == 0) {
				System.out.println("");
			}

			int bytesOnThisLine;

			if (y < numberOfLines - 1) {
				bytesOnThisLine = bytesPerLine;
			} else {
				bytesOnThisLine = numberOfBytes % bytesPerLine;
			}

			for (int x = 0; x < bytesOnThisLine; x++) {
				if (x > 0 && x % bytesPerColumn == 0) {
					System.out.print("  ");
				}

				int byteIndex = y * bytesPerLine + x;
				byte byteCurrent = bytesToWrite[byteIndex];

				String byteAsString = Integer.toHexString(byteCurrent & 0xFF);
				if (byteAsString.length() < 2) {
					byteAsString = "0" + byteAsString;
				}

				byteAsString += " ";

				System.out.print(byteAsString);
			}

			System.out.println();
		}

		System.out.println(horizontalRule);
	}

}
