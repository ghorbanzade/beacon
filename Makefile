BIN_DIR = bin
DOC_DIR = doc
OUT_DIR = $(BIN_DIR)/doc
TEX_DIR = $(DOC_DIR)/tex
TEX = $(TEX_DIR)/main.tex
PDF = $(BIN_DIR)/doc/main.pdf

.PHONY: all docs code tidy clean

all: code

docs: directories $(PDF) tidy

directories: 
	@mkdir -p $(BIN_DIR)
	@mkdir -p $(OUT_DIR)

$(PDF): $(TEX)
	@echo -n "  $(@F)... " && \
	pdflatex -halt-on-error -output-directory $(OUT_DIR) $(TEX_DIR)/$(@F:.pdf=.tex) > /dev/null && \
	pdflatex -halt-on-error -output-directory $(OUT_DIR) $(TEX_DIR)/$(@F:.pdf=.tex) > /dev/null
	@echo "Done."
code:
	@ant

tidy:
	@find $(OUT_DIR) -name '*.log' -delete
	@find $(OUT_DIR) -name '*.aux' -delete

clean:
	@ant clean
	@rm -rf $(BIN_DIR)
