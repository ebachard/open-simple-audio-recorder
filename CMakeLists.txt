# Eric Bachard  2020 09 18 
# License MIT

cmake_minimum_required(VERSION 3.0.0)

project(osrecorder C)

if(NOT CMAKE_BUILD_TYPE)
    set(CMAKE_BUILD_TYPE Release)
endif()

set(CMAKE_C_STANDARD 99)
set(CMAKE_VERBOSE_MAKEFILE OFF)


if (CMAKE_SYSTEM MATCHES Linux)
    #testing some warning flags. Be carefull: some can be DANGEROUS when badly used
    ##  set(CMAKE_CXX_FLAGS "-g -DDEBUG -DLinux -Wall -Wextra -Wno-unused-parameter -Wno-unused-function -Wunused-variable -Wunused-value -Wshadow -Wcast-qual -Wcast-align -Wwrite-strings -Wsign-compare -fms-extensions -Wfloat-equal")
    set(CMAKE_C_FLAGS "-Wall -Wextra  -DLinux")
endif()

set(C_FILES
    src/nixtla-audio/nixtla-audio.c
    src/osaudiorecorder.c
)

add_executable(test_osrecorder src/main.c ${C_FILES})

file(GLOB_RECURSE INC src/*.h)

add_library(${PROJECT_NAME} ${C_FILES})

target_include_directories(${PROJECT_NAME} PUBLIC ${PROJECT_SOURCE_DIR}/src )

target_link_libraries(test_osrecorder ${PROJECT_NAME})
target_link_libraries(${PROJECT_NAME} opus ogg openal FLAC pthread)


