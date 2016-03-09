#
# CS638: Applied Machine Learning
# Copyright 2016 Pejman Ghorbanzade <mail@ghorbanzade.com>
# Creative Commons Attribution-ShareAlike 4.0 International License
# More info: https://bitbucket.org/ghorbanzade/umb-cs638-2016s
#

# clear workspace
rm(list = ls())

# remove all plots if there are any
if (!is.null(dev.list())) trash <- dev.off()

# loading database 1
data1 = read.table("dat/hw04/data1.txt", sep=",")
colnames(data1) <- c('exam1', 'exam2', 'status')

data11 <- data1[data1[, "status"] == 1, , drop=FALSE]
data12 <- data1[data1[, "status"] == 0, , drop=FALSE]

##
# visualize dataset
##
png(filename="bin/png/hw04-01.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(floor(min(data1$exam1)/5)*5,
	ceiling(max(data1$exam1)/5)*5,
	length.out=5
	)
yrange <- seq(floor(min(data1$exam2)/5)*5,
	ceiling(max(data1$exam2)/5)*5,
	length.out=5
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(data11$exam1, data11$exam2, type="p", col="blue", pch=3)
points(data12$exam1, data12$exam2, type="p", col="red", pch=1)
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))
legend("topright", inset=0.01,
	legend = c(expression("admitted"), expression("rejected")),
	col = c("blue", "red"),
	pch = c(3, 1),
	cex = 0.8
	);

grid(10, 10)
box()
title(main="Student Admission Results Based on Exam Scores", font.main=1)
title(xlab="Score in Exam 1", col.lab='black')
title(ylab="Score in Exam 2", col.lab='black')
trash <- dev.off()

##
# loading database 2
##
data2 = read.table("dat/hw04/data2.txt", sep=",")
colnames(data2) <- c('exam1', 'exam2', 'status')

data21 <- data2[data2[, "status"] == 1, , drop=FALSE]
data22 <- data2[data2[, "status"] == 0, , drop=FALSE]

##
# visualize dataset
##
png(filename="bin/png/hw04-02.png",
	height=768, width=1024, res=150, units="px", bg="white")

xrange <- seq(floor(min(data2$exam1)*4)/4,
	ceiling(max(data2$exam1)*4)/4,
	length.out=4
	)
yrange <- seq(floor(min(data2$exam2)*4)/4,
	ceiling(max(data2$exam2)*4)/4,
	length.out=4
	)
plot(0, 0, type="n", axes=F, ann=F,
	xlim=range(xrange), ylim=range(yrange))
points(data21$exam1, data21$exam2, type="p", col="blue", pch=3)
points(data22$exam1, data22$exam2, type="p", col="red", pch=1)
axis(1, at=xrange, labels=round(xrange, 3))
axis(2, at=yrange, labels=round(yrange, 3))

legend("topright", inset=0.01,
	legend = c(expression("admitted"), expression("rejected")),
	col = c("blue", "red"),
	pch = c(3, 1),
	cex = 0.8
	);
grid(10, 10)
box()
title(main="Student Admission Results Based on Exam Scores", font.main=1)
title(xlab="Score in Exam 1", col.lab='black')
title(ylab="Score in Exam 2", col.lab='black')
trash <- dev.off()
