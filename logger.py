import loguru

loguru.logger.add("info.log", level="INFO", format="{process} {message}", enqueue=True)
logger = loguru.logger
