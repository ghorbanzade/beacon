DOCS = hw01 hw02 hw03
BIND_DOC = cs630

TOP_DIR = .
BIN_DIR = $(TOP_DIR)/bin
SRC_DIR = $(TOP_DIR)/src
TEX_DIR = $(SRC_DIR)/tex
DOC_DIR = $(BIN_DIR)/doc

DOC_TEX = $(foreach NUM, $(DOCS), $(TEX_DIR)/$(NUM)/$(NUM).tex)
DOC_PDF = $(foreach NUM, $(DOCS), $(DOC_DIR)/$(NUM).pdf)

.PHONY: all dirs docs bind tidy clean

all: dirs docs bind tidy

dirs:
	@mkdir -p $(BIN_DIR)
	@mkdir -p $(DOC_DIR)

docs: $(DOC_PDF)

$(DOC_PDF): $(DOC_TEX)
	@echo -n "  $(@F)... "
	pdflatex -halt-on-error -output-directory $(DOC_DIR) $(TEX_DIR)/$(@F:.pdf=)/$(@F:.pdf=.tex)
	@pdflatex -halt-on-error -output-directory $(DOC_DIR) $(TEX_DIR)/$(@F:.pdf=)/$(@F:.pdf=.tex) > /dev/null
	@echo "Done."

bind:
	@echo -n "  Binding documents... "
	@gs -dBATCH -dNOPAUSE -q -sDEVICE=pdfwrite -sOutputFile=$(BIN_DIR)/$(BIND_DOC).pdf $(DOC_PDF)
	@echo "Done."

tidy:
	@echo -n "  Removing unneeded files... "
	@find $(BIN_DIR) -name '*.log' -delete
	@find $(BIN_DIR) -name '*.aux' -delete
	@find $(BIN_DIR) -name '*.out' -delete
	@echo "Done."

clean:
	@echo -n "  Removing $(BIN_DIR)... "
	@rm -rf $(BIN_DIR)
	@echo "Done."
