#
# CS671: Machine Learning
# Copyleft 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
# More info: https://bitbucket.org/ghorbanzade/umb-cs671-2015s
#
# The author has placed this file in the public domain.
# He makes no warranty and accepts no liability for this file.
#

# clear workspace
rm(list = ls())

if (!is.null(dev.list())) trash <- dev.off()

# loading dataset from csv file
training_set <- read.csv("dat/dataset_training.csv", header=TRUE, sep=",")

# plot training dataset
png(filename="bin/png/f01-01.png",
	height=768, width=1024, res=150, units="px", bg="white")

plot(0:10, 0:10, type = "n", ann=FALSE, axes=FALSE, xaxs="i", yaxs="i")
for (i in 1:dim(training_set)[1]) {
  if(training_set[i,3]=="true")
    points(training_set[i,1],training_set[i,2],pch=21,col="black",bg="black")
  else
    points(training_set[i,1],training_set[i,2],pch=21,col="black",bg="white")
}
axis(1, 1:10, at=1:10)
axis(2, 1:10, at=1:10)

grid(10, 10)
box()
title(xlab="X-Axis")
title(ylab="Y-Axis")
title(main="Training Dataset", font.main=1)
trash <- dev.off()

# building classifiers
library("kernlab")

# Build a classifer using a linear kernel function
kernels <- c("vanilladot", "rbfdot", "polydot", "anovadot", "besseldot", "laplacedot", "splinedot", "tanhdot")
training_error <- matrix(0,1,length(kernels))
exec_time <- matrix(0,length(kernels),5)
for (i in 1:length(kernels)) {
  ptm <- proc.time()
  classifier <- ksvm(value~., data=training_set, kernel=kernels[i])
  training_error[i] <- classifier@error
  exec_time[i,1:5] <- proc.time() - ptm
}
exec_time <- exec_time[,1:3]

# plot training_error for classifiers
png(filename="bin/png/f01-02.png",
	height=768, width=1024, res=150, units="px", bg="white")

plot(1:length(kernels), training_error, type="o", col="blue", ann=F, axes=F)
plot.window(ylim=c(min(training_error),max(training_error)),xlim=c(1,8))
points(1:length(kernels), training_error,pch=21,col="blue",bg="white")
axis(1, at=1:length(kernels), lab=kernels, cex.axis=0.8)
axis(2, las=1)

grid()
box()
title(xlab="Kernel")
title(ylab="Error Rate")
title(main="Training Error for Different Kernels", font.main=1)
trash <- dev.off()

# plot execution time of classifiers
png(filename="bin/png/f01-03.png",
	height=768, width=1024, res=150, units="px", bg="white")

plot(1:length(kernels), exec_time[,1], type="o", col="blue", ann=F, axes=F)
points(1:length(kernels), exec_time[,1],pch=21,col="blue",bg="white")
axis(1, at=1:length(kernels), lab=kernels, cex.axis=0.8)
axis(2, las=1)

grid()
box()
title(xlab="Kernel")
title(ylab="Execution Time")
title(main="Execution Time of Constructing Different Kernels", font.main=1)
trash <- dev.off()

# plot execution time based on training error
png(filename="bin/png/f01-04.png",
	height=768, width=1024, res=150, units="px", bg="white")

plot(training_error, exec_time[,1], type="o", col="blue", ann=F, axes=F)
points(training_error,exec_time[,1],pch=21,col="blue",bg="white")
axis(1, )
axis(2, las=1)

grid()
box()
title(xlab="Training Error")
title(ylab="Execution Time")
title(main="Execution Time Based on Training Errors", font.main=1)
trash <- dev.off()
