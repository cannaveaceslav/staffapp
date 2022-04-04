FROM node:latest as builde

RUN mkdir -p /app

WORKDIR /app

COPY . .

RUN npm install
RUN npm run build --prod

CMD ["npm", "start"]


