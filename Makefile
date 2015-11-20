BIN_DIR = bin
DOC_DIR = doc

.PHONY: all docs code clean

all: code

docs:
	@$(MAKE) -C $(DOC_DIR)

code:
	@ant

clean:
	@ant clean
	@$(MAKE) --no-print-directory clean -C $(DOC_DIR);
	@rm -rf $(BIN_DIR)
