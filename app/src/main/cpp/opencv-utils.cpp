//
// Created by mucte on 26.10.2021.
//

#include "opencv-utils.h"
#include <opencv2/imgproc.hpp>

void flipImage(Mat src) {
    flip(src, src, 0);
}

void blurImage(Mat src, float sigma) {
    GaussianBlur(src, src, Size(), sigma);
}