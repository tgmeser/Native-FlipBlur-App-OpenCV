# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.10.2)

# Declares and names the project.

project("blurimageapp")

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

# -------------------------------- OPENCV ------------------------------------
set(OpenCV_STATIC ON)     # STEP -1
set(OpenCV_DIR $ENV{OPENCV_ANDROID}/sdk/native/jni)     # STEP -2
find_package(OpenCV REQUIRED)     # STEP -3


add_library( # Sets the name of the library.
        # REMOVE project("blurimage")
        native-lib       # STEP -4


        # Sets the library as a shared library.
        SHARED

        opencv-utils.cpp        # STEP 5

        # Provides a relative path to your source file(s).
        native-lib.cpp)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)


# STEP -6
find_library(jnigraphics-lib jnigraphics)



# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        #REMOVE prokect("bluimageapp")
        native-lib     # STEP -7

        ${OpenCV_LIBS}      # STEP -8
        ${jnigraphics-lib}     # STEP -9

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})