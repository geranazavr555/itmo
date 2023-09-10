cmake_minimum_required(VERSION 3.22)
project(lab)

set(CMAKE_CXX_STANDARD 17)
add_compile_options("-g" "-O2")

add_executable(lab main.cpp)
add_executable(xagen xagen.cpp)
add_executable(main_viterbi main_viterbi.cpp)
