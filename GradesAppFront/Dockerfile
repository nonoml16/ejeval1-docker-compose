FROM node:18.18.0-alpine3.18 as build
WORKDIR /app/
COPY . /app/
RUN npm install
COPY . .
RUN npm run build

FROM nginx:alpine
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build /app/dist/grades-app-front /usr/share/nginx/html
