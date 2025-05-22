# Stage 1: Build stage for C++ compiler and build tools
FROM debian:bullseye-slim as builder
RUN apt-get update && apt-get install -y --no-install-recommends \
    g++ \
    make \
    && rm -rf /var/lib/apt/lists/*

# Final stage
FROM python:3.12-slim

# Install minimal runtime dependencies
RUN apt-get update && apt-get install -y --no-install-recommends \
    openjdk-17-jre-headless \
    g++ \
    libstdc++6 \
    && rm -rf /var/lib/apt/lists/* \
    && pip install --no-cache-dir numpy pandas

# Create non-root user
RUN useradd -m -s /bin/bash coderunner
WORKDIR /app

# Set security limits directly
RUN mkdir -p /etc/security/limits.d && \
    echo "coderunner soft nproc 50" > /etc/security/limits.d/coderunner.conf && \
    echo "coderunner hard nproc 100" >> /etc/security/limits.d/coderunner.conf && \
    echo "coderunner soft nofile 1024" >> /etc/security/limits.d/coderunner.conf && \
    echo "coderunner hard nofile 2048" >> /etc/security/limits.d/coderunner.conf

# Set permissions
RUN chown -R coderunner:coderunner /app
USER coderunner

# Set security-related environment variables
ENV PYTHONDONTWRITEBYTECODE=1 \
    PYTHONUNBUFFERED=1 \
    JAVA_SECURITY_POLICY="sandbox.policy"

# Working directory for code execution
WORKDIR /app/workspace

# Default command
CMD ["bash"]