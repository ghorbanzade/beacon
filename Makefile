BIN_DIR = bin
SRC_DIR = src
DOC_DIR = $(SRC_DIR)/doc
TEX_DIR = $(DOC_DIR)/tex
PDF_DIR = $(BIN_DIR)/documents

DOCS = hw01
DOC_TEX = $(foreach NUM, $(DOCS), $(TEX_DIR)/$(NUM)/$(NUM).tex)
DOC_PDF = $(foreach NUM, $(DOCS), $(PDF_DIR)/$(NUM).pdf)

.PHONY: all dirs docs code tidy clean

all: code

docs: dirs $(DOC_PDF) code tidy

dirs: 
	@mkdir -p $(BIN_DIR)
	@mkdir -p $(PDF_DIR)

$(DOC_PDF): $(DOC_TEX)
	@echo -n "  $(@F)... "
	@pdflatex -halt-on-error -output-directory $(PDF_DIR) $(TEX_DIR)/$(@F:.pdf=)/$(@F:.pdf=.tex) > /dev/null
	@pdflatex -halt-on-error -output-directory $(PDF_DIR) $(TEX_DIR)/$(@F:.pdf=)/$(@F:.pdf=.tex) > /dev/null
	@echo "Done."

code:
	@ant

tidy:
	@find $(PDF_DIR) -name '*.log' -delete
	@find $(PDF_DIR) -name '*.out' -delete
	@find $(PDF_DIR) -name '*.aux' -delete

clean:
	@ant clean
	@rm -rf $(BIN_DIR)
