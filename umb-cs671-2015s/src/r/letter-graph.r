#
# CS671: Machine Learning
# Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
# Creative Commons Attribution-ShareAlike 4.0 International License
# More info: https://bitbucket.org/ghorbanzade/umb-cs671-2015f
#

# clear workspace
rm(list = ls())

# enable traceback on error messages
options(error=traceback)

# remove all plots if there are any
if (!is.null(dev.list())) trash <- dev.off()

args = commandArgs(trailingOnly=TRUE)
if (0 == length(args)) {
  stop("expected path to root directory of course", call.=FALSE)
}
pngdir <- args[1]
courseNameFull <- "umb-cs671-2015s"

# loading dataset from csv file
set <- read.csv("dat/letter.csv", header=TRUE, sep=",")
training_set <- set[1:1000, ]

#
# building classifiers
#
library("kernlab")
kernels <- c("vanilladot", "rbfdot", "polydot", "anovadot", "besseldot", "laplacedot", "splinedot", "tanhdot")
training_error <- matrix(0,1,length(kernels))
exec_time <- matrix(0,length(kernels),5)
for (i in 1:length(kernels)) {
  ptm <- proc.time()
  classifier <- ksvm(lettr~., data=training_set, kernel=kernels[i])
  training_error[i] <- classifier@error
  exec_time[i,1:5] <- proc.time() - ptm
}
exec_time <- exec_time[,1:3]

#
# plot training_error for classifiers
#
png(filename=file.path(pngdir, paste(courseNameFull, "-letter-01", ".png", sep="")),
	height=768, width=1024, res=150, units="px", bg="white")

plot(1:length(kernels), training_error, type="o", col="blue", ann=F, axes=F)
plot.window(ylim=c(min(training_error),max(training_error)),xlim=c(1,8))
axis(1, at=1:length(kernels), lab=kernels)
axis(2, las=1)

grid()
box()
title(xlab="Kernel")
title(ylab="Error Rate")
title(main="Training Error for Different Kernels", font.main=1)
trash <- dev.off()

#
# plot execution time of classifiers
#
png(filename=file.path(pngdir, paste(courseNameFull, "-letter-02", ".png", sep="")),
	height=768, width=1024, res=150, units="px", bg="white")

plot(1:length(kernels), exec_time[,1], type="o", col="blue", ann=F, axes=F)
plot.window(ylim=c(min(exec_time),max(exec_time)),xlim=c(1,8))
axis(1, at=1:length(kernels), lab=kernels, cex.axis=0.8)
axis(2, las=1)

grid()
box()
title(xlab="Kernel")
title(ylab="Execution Time")
title(main="Execution Time of Constructing Different Kernels", font.main=1)
trash <- dev.off()

#
# plot execution time based on training_error
#
png(filename=file.path(pngdir, paste(courseNameFull, "-letter-03", ".png", sep="")),
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(0, 1, length.out=6)
yrange <- seq(floor(min(exec_time)/5)*5,
	ceiling(max(exec_time)/5)*5,
	length.out=6
	)

plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(training_error, exec_time[,1], type="p", col="blue")
axis(1, at=xrange)
axis(2, at=yrange, las=1)

grid()
box()
title(xlab="Training Error")
title(ylab="Execution Time")
