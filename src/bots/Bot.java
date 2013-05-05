package bots;

import Exception.BotException;

public interface Bot {
	/**
	 * 
	 * @param playername
	 * @throws BotException
	 */
	void start(String playername)throws BotException;
	/**
	 * 
	 */
	void doAction();
	/**
	 * 
	 * @param playername
	 * @throws BotException
	 */
	void stop(String playername) throws BotException;
}
