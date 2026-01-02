# Lumber Inventory System

A mobile-friendly, web-based lumber inventory management system designed for woodworking workshops. Built with Python/Flask and optimized to run on a Raspberry Pi for local, private data storage.

## Overview

This application helps you keep track of your lumber inventory in your woodworking workshop. Each piece of lumber is tracked by:

- **Species** - Type of wood (e.g., Oak, Walnut, Maple, Cherry)
- **Dimensions** - Length, width, and thickness (in inches)
- **Surface** - Whether the wood is planed or rough
- **Location** - Custom storage locations (user-defined)

The system runs as a web server on your Raspberry Pi, allowing you to access and manage your inventory from any device on your local network, including your phone.

## Features

- **View Inventory** - Browse all lumber with sorting by date added
- **Search & Filter** - Find lumber by species, location, surface finish, tags, or length range
- **Add Lumber** - Quickly add new pieces to your inventory
- **Edit Lumber** - Update details or move lumber to different shelves
- **Delete Lumber** - Remove pieces from inventory when used
- **Mobile-Responsive** - Optimized for phone use with Bootstrap 5
- **Local Storage** - All data stored securely on your Raspberry Pi using SQLite

## Technology Stack

- **Backend**: Python 3 with Flask
- **Database**: SQLite (file-based, no server required)
- **ORM**: Flask-SQLAlchemy
- **Forms**: Flask-WTF with WTForms validation
- **Frontend**: HTML5, CSS3, Bootstrap 5
- **Icons**: Bootstrap Icons

## Project Structure

```
lumber-inventory/
├── app.py              # Main Flask application with routes
├── models.py           # SQLAlchemy database models
├── forms.py            # WTForms form definitions
├── templates/          # Jinja2 HTML templates
│   ├── base.html       # Base template with navigation
│   ├── index.html      # Inventory list and search page
│   ├── add_lumber.html # Form to add new lumber
│   └── edit_lumber.html# Form to edit existing lumber
├── static/
│   └── style.css       # Custom CSS styles
├── instance/
│   └── inventory.db    # SQLite database (created on first run)
├── requirements.txt    # Python dependencies
├── context.md          # Project specification document
└── README.md           # This file
```

## Installation

### Prerequisites

- Python 3.8 or higher
- pip (Python package manager)

### Quick Start

1. **Navigate to the project directory:**

   ```bash
   cd /path/to/lumber-inventory
   ```

2. **Create a virtual environment (recommended):**

   ```bash
   python3 -m venv venv
   source venv/bin/activate
   ```

3. **Install dependencies:**

   ```bash
   pip install -r requirements.txt
   ```

4. **Run the application:**

   ```bash
   python app.py
   ```

5. **Access the application:**

   Open your browser and go to: `http://localhost`

   > **Note:** Port 80 requires root/sudo privileges. Run with `sudo python app.py` if needed.

The SQLite database will be created automatically on first run.

## Accessing from Your Phone

1. Ensure your phone is connected to the same WiFi network as the computer/Raspberry Pi
2. Find your device's IP address:
   - On Raspberry Pi/Linux: `hostname -I`
   - On macOS: `ipconfig getifaddr en0`
3. Open your phone's browser and navigate to: `http://<ip-address>`

If mDNS is configured on your Raspberry Pi:
- `http://raspberrypi.local`

## Usage Guide

### Adding Lumber

1. Click the "Add Lumber" button on the home page
2. Fill in the species (e.g., "Red Oak", "Black Walnut")
3. Enter dimensions in inches (length, width, thickness)
4. Select whether it's planed or rough
5. Choose an existing location or create a new one
6. Optionally add tags
7. Click "Add Lumber"

### Searching Inventory

Use the search panel on the home page to filter by:
- **Species** - Partial match (e.g., "oak" finds "Red Oak" and "White Oak")
- **Location** - Filter by storage location (dropdown of all existing locations)
- **Surface** - Planed or Rough
- **Tag** - Filter by tag
- **Length Range** - Minimum and maximum length

### Editing Lumber

Click the pencil icon next to any lumber entry to:
- Update any field
- Move to a different location (select existing or create new)
- Change dimensions after cutting
- Manage tags

### Deleting Lumber

Click the trash icon or use the "Delete" button on the edit page to remove lumber from inventory.

## Raspberry Pi Deployment

### Running as a System Service

To run the application automatically on boot:

1. **Create a systemd service file:**

   ```bash
   sudo nano /etc/systemd/system/lumber-inventory.service
   ```

2. **Add the following content (adjust paths as needed):**

   ```ini
   [Unit]
   Description=Lumber Inventory Web Application
   After=network.target

   [Service]
   User=pi
   WorkingDirectory=/home/pi/lumber-inventory
   Environment="PATH=/home/pi/lumber-inventory/venv/bin"
   ExecStart=/home/pi/lumber-inventory/venv/bin/python3 app.py
   Restart=always
   RestartSec=10

   [Install]
   WantedBy=multi-user.target
   ```

3. **Enable and start the service:**

   ```bash
   sudo systemctl daemon-reload
   sudo systemctl enable lumber-inventory
   sudo systemctl start lumber-inventory
   ```

4. **Check status:**

   ```bash
   sudo systemctl status lumber-inventory
   ```

5. **View logs:**

   ```bash
   sudo journalctl -u lumber-inventory -f
   ```

### Production Recommendations

For production use, consider:

1. **Set a secure secret key:**

   ```bash
   export SECRET_KEY="$(python3 -c 'import secrets; print(secrets.token_hex(32))')"
   ```

2. **Use a production WSGI server:**

   ```bash
   pip install gunicorn
   sudo gunicorn -w 2 -b 0.0.0.0:80 app:app
   ```

3. **Set up a static IP** for consistent access from your phone

## Backup

The database file is stored at `instance/inventory.db`. To backup:

```bash
cp instance/inventory.db instance/inventory_backup_$(date +%Y%m%d).db
```

Consider setting up a cron job for automatic backups.

## Troubleshooting

### Cannot access from phone

- Verify both devices are on the same network
- Check firewall settings: `sudo ufw allow 80`
- Verify the server is running: `curl http://localhost`

### Database errors

- Ensure the `instance` directory is writable
- Check disk space: `df -h`

### Port already in use

- Find and kill the process: `sudo lsof -i :80`
- Or use a different port by modifying `app.run(port=8080)` in `app.py`

### Permission denied on port 80

- Port 80 requires elevated privileges. Run with: `sudo python app.py`
- Or use a port above 1024 (e.g., 8080) which doesn't require sudo

## Future Enhancements

Potential features for future development:

- User authentication (Flask-Login)
- Barcode/QR code scanning for quick lookup
- Export inventory to CSV/PDF
- Photo attachments for lumber pieces
- Board foot calculations
- Low stock alerts
- Usage history and statistics

## License

This project is open source and available for personal use.
