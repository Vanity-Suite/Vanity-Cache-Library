package com.github.vanity.cache;

import com.github.vanity.io.impl.InputStream;
import com.github.vanity.io.impl.OutputStream;

/**
 * A class serving as a container where we can read from and write to.
 * @author Displee
 */
public interface Container {

	/**
	 * Used to read data from an index, an archive or a file.
	 */
    boolean read(InputStream inputStream);

	/**
	 * Write data to an index, archive or file.
	 */
    byte[] write(OutputStream outputStream);

}
