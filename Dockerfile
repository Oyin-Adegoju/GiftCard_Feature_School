FROM node:alpine
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN npm install -g @angular/cli
RUN npm install
ENV PATH /usr/local/bin:$PATH
CMD ["ng", "serve", "--host", "0.0.0.0"]
