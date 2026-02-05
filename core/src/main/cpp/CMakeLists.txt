cmake_minimum_required(VERSION 3.22.1)

project("virtualworld")

add_library(
        virtualworld
        SHARED
        native-bridge.cpp
        hook-engine.cpp
        io-redirect.cpp)

find_library(
        log-lib
        log)

target_link_libraries(
        virtualworld
        ${log-lib})
