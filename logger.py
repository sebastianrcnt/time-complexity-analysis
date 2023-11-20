import loguru

loguru.logger.add("info.log", level="INFO", format="{message}")
logger = loguru.logger
