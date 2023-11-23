FROM node:19-alpine AS build

WORKDIR /app/youquiz_front

COPY package*.json ./

RUN npm install -g @angular/cli

COPY . .

RUN npm install && \
    ng build

FROM nginx:1.17.1-alpine

COPY --from=build /app/youquiz_front/dist/you-quiz-front /usr/share/nginx/html

EXPOSE 80
