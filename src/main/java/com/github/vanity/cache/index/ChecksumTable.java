package com.github.vanity.cache.index;

import com.github.vanity.CacheLibrary;
import com.github.vanity.cache.Container;
import com.github.vanity.io.impl.InputStream;
import com.github.vanity.io.impl.OutputStream;

import java.nio.channels.FileChannel;

/**
 * A class representing the checksum table.
 * @author Displee
 */
public class ChecksumTable extends Index implements Container {

	/**
	 * The data of this checksum table.
	 */
	private byte[] data;

	/**
	 * Constructs a new {@code ChecksumTable} {@code Object}.
	 * @param id The index.
	 * @param randomAccessFile The random access file of this index.
	 */
	public ChecksumTable(CacheLibrary origin, int id, FileChannel randomAccessFile) {
		super(origin, id, randomAccessFile);
	}

	@Override
	public boolean read(InputStream inputStream) {
		for(int i = 0; i < super.origin.getIndices().length; i++) {
			int crc = inputStream.readInt();
			if (crc > 0) {
				super.origin.getIndex(i).setCRC(crc);
			}
			int revision = inputStream.readInt();
			if (revision > 0) {
				super.origin.getIndex(i).setRevision(revision);
			}
		}
		return true;
	}

	@Override
	public byte[] write(OutputStream outputStream) {
		for(final Index index : super.origin.getIndices()) {
			outputStream.writeInt(index == null ? 0 : index.getCRC());
			outputStream.writeInt(index == null ? 0 : index.getRevision());
		}
		return data = outputStream.flip();
	}

	/**
	 * Get the checksum table data.
	 * @return {@code data}
	 */
	public byte[] getData() {
		return data;
	}

}
