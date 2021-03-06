package dataretrievers;

import java.sql.SQLException;
import java.util.Queue;

import org.apache.log4j.Logger;

import utils.DatabaseAccess;

/**
 * 
 * @author abhinav.sunderrajan
 * 
 */
public abstract class DatabaseLoader<T> implements Runnable {

	protected Queue<T> xmlDocQueue;
	protected DatabaseAccess access;
	private static final Logger LOGGER = Logger.getLogger(DatabaseLoader.class);

	public DatabaseLoader(Queue<T> xmlDocQueue, final DatabaseAccess access) {
		this.xmlDocQueue = xmlDocQueue;
		this.access = access;
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					access.closeConnection();
				} catch (SQLException e) {
					LOGGER.error("Error closing SQL database connection", e);
				}
			}
		}));
	}

}
