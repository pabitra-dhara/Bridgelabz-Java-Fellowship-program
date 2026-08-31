# Bookstore React Frontend

Responsive React frontend based on the supplied Bookstore UI PDF. The UI includes the pages shown in the reference: book listing, book details/feedback, login, signup, cart/checkout, order success, profile, orders, wishlist, and the logged-out wishlist state. The screenshots in the PDF were used as the visual reference. fileciteturn7file0L1-L1

## Tech used

- React + JSX
- React Router
- Axios
- Bootstrap 5
- SCSS
- Context API
- Hooks (`useState`, `useEffect`, `useMemo`, `useRef`, `useContext`)
- Axios request/response interceptors
- Controlled and uncontrolled form examples
- Conditional rendering, lists and keys
- Portal for modal
- Higher-order component (`withAuth`)
- Render-prop component (`ResponsiveRender`)
- React Profiler
- CSS media queries and responsive layout
- Small CSS animations

## Run in IntelliJ / VS Code

1. Install Node.js LTS.
2. Open this folder.
3. Copy `.env.example` to `.env`.
4. Keep `VITE_API_BASE_URL` empty (or remove the line). The Vite dev server proxies `/bookstore_book/*` and `/bookstore_user/*` to `http://localhost:8080`, which avoids browser CORS errors during local development.
5. Run:

```bash
npm install
npm run dev
```

6. Open the URL printed by Vite, normally `http://localhost:5173`.

## Backend routes expected

The frontend is prepared for the Spring Cloud Gateway used in the Bookstore backend:

- `GET /bookstore_book/get/book`
- `POST /bookstore_book/add/feedback/{product_id}`
- `POST /bookstore_user/add_cart_item/{product_id}`
- `PUT /bookstore_user/cart_item_quantity/{cartItem_id}`
- `DELETE /bookstore_user/remove_cart_item/{cartItem_id}`
- `GET /bookstore_user/get_cart_items`
- `POST /bookstore_user/add_wish_list/{product_id}`
- `DELETE /bookstore_user/remove_wishlist_item/{product_id}`
- `GET /bookstore_user/get_wishlist_items`
- `POST /bookstore_user/add/order`

Login/register endpoint names can differ between backend implementations. They are centralized in `src/services/authService.js`, so they can be changed in one place.

## Authentication

JWT is stored in `localStorage` under `bookstore_token`. Axios automatically adds:

`Authorization: Bearer <token>`

The response interceptor clears the session on HTTP 401.

## Demo mode

If the backend is not running, you can temporarily set:

`VITE_DEMO_MODE=true`

The UI then uses local demo data for browsing and localStorage for cart/wishlist/order state. For your actual Spring Boot project, use `false`.

## Note about the requested curriculum topics

React uses JSX, components, props, state, hooks, events, conditional rendering, lists/keys, refs, portals, context, HOC, render props, Axios interceptors, responsive media queries, and Profiler in this project. "Reactive Forms", Angular decorators, and Angular lifecycle APIs are not native React concepts, so equivalent React patterns are used instead of adding Angular code to a React application.


## CORS / local development fix

If the browser previously showed `No 'Access-Control-Allow-Origin' header` or a failed preflight request, this frontend now uses the Vite development proxy. Start the backend on `http://localhost:8080` and start Vite with `npm run dev`.

Do not set `VITE_API_BASE_URL=http://localhost:8080` in `.env` while using the Vite proxy; that makes Axios call the backend directly from the browser and CORS will occur again.

For production, configure CORS on the backend/gateway to allow the deployed frontend origin instead of relying on the Vite proxy.
