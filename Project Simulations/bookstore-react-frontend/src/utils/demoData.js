export const demoBooks = [
  { id: 1, bookName: "Don't Make Me Think", author: "Steve Krug", description: "A practical guide to web usability and user experience.", price: 1500, discountPrice: 1200, quantity: 10, rating: 4.5, feedbackCount: 20, image: "/images/dont-make-me-think.jpg" },
  { id: 2, bookName: "React Material-UI", author: "Cookbook", description: "Build modern interfaces with React and Material UI.", price: 780, discountPrice: 650, quantity: 8, rating: 4.5, feedbackCount: 12, image: "/images/ux-design.jpg" },
  { id: 3, bookName: "Mastering SharePoint Framework", author: "SharePoint Team", description: "Learn modern SharePoint development patterns.", price: 1100, discountPrice: 900, quantity: 6, rating: 4.2, feedbackCount: 15, image: "/images/sharepoint.jpg" },
  { id: 4, bookName: "UX For Dummies", author: "UX Team", description: "An accessible introduction to user experience.", price: 950, discountPrice: 800, quantity: 0, rating: 4.1, feedbackCount: 9, image: "/images/ux-dummies.jpg" },
  { id: 5, bookName: "A Project Guide to UX Design", author: "Russ Unger", description: "A practical project-focused UX design guide.", price: 1350, discountPrice: 1150, quantity: 7, rating: 4.4, feedbackCount: 18, image: "/images/ux-design.jpg" },
  { id: 6, bookName: "Group Discussion", author: "Learning Series", description: "Communication and group discussion techniques.", price: 700, discountPrice: 600, quantity: 9, rating: 4.0, feedbackCount: 7, image: "/images/group-discussion.jpg" },
  { id: 7, bookName: "Lean UX", author: "Jeff Gothelf", description: "Design great products with lean UX principles.", price: 1250, discountPrice: 1000, quantity: 5, rating: 4.5, feedbackCount: 20, image: "/images/lean-ux.jpg" },
  { id: 8, bookName: "The Design of Everyday Things", author: "Don Norman", description: "Classic principles for human-centered design.", price: 1500, discountPrice: 1250, quantity: 4, rating: 4.6, feedbackCount: 30, image: "/images/design-everyday.jpg" }
];

export function bookImage(book) {
  return book.image || demoBooks.find((b) => Number(b.id) === Number(book.id))?.image || "/images/dont-make-me-think.jpg";
}
