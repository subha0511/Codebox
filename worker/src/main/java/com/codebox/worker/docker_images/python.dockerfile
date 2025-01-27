# Dockerfile
FROM python:3.12.8-slim

# Install dependencies
RUN pip install numpy pandas

# Set working directory
WORKDIR /app

# Copy a script (optional)
COPY run_code.py /app/run_code.py

# Default command (optional)
CMD ["python", "run_code.py"]